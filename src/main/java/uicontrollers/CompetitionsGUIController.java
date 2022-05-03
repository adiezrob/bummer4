package uicontrollers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import domain.Competition;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import ui.Manager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CompetitionsGUIController {

    @FXML
    private ImageView LogoImage;

    @FXML
    private TextField areaCCField;

    @FXML
    private TextField areaIDField;

    @FXML
    private TextField areaNameField;

    @FXML
    private TextField compIDField;

    @FXML
    private TextField compNameField;

    @FXML
    private Button nextBtn;

    @FXML
    private Button previousBtn;

    private List<Competition> competitionList;

    private int index;

    private Manager manager;

    @FXML
    void goNext() {
        index = (index + 1) % competitionList.size();
        setLabels();
    }

    @FXML
    void goPrevious() {
        index = (index - 1);
        if(index == -1){
            index = competitionList.size()-1;
        }
        setLabels();

    }

    private void setLabels() {
        LogoImage.setImage(null);
        String comp = competitionList.get(index).toString();
        compIDField.setText(comp.split("\\{")[1].split(",")[0].split("=")[1]);
        compNameField.setText(comp.split(",")[1].split("'")[1]);
        areaIDField.setText((comp.split(",")[3].split("=")[2]));
        areaNameField.setText(comp.split(",")[4].split("'")[1]);
        areaCCField.setText(comp.split(",")[5].split("'")[1]);
        setImage(comp);
    }

    public void setUp(List<Competition> compList){
        this.competitionList = compList;
        this.index=0;
        goNext();
    }

    @FXML
    void initialize(){
        manager = new Manager();
        String body = manager.request("competitions");
        Gson gson = new Gson();
        JsonObject jsonObject;
        CompetitionsGUIController GUIcont = new CompetitionsGUIController();

        jsonObject = gson.fromJson(body, JsonObject.class);
        Type competitionListType = new TypeToken<ArrayList<Competition>>(){}.getType();
        competitionList = gson.fromJson((jsonObject.get("competitions")), competitionListType);
        System.out.println("competitions = " + competitionList);
        setLabels();
    }

    public void setImage(String comp) {
        String link = comp.split(",")[2].split("'")[1];
        if(!link.equals("null")){
            URL url = null;
            try {
                url = new URL(link);
            } catch (MalformedURLException e) {
                return;
            }
            BufferedImage image = null;
            System.out.println("link = " + link);
            if(link.substring(link.length() - 3).equals("svg")){
                BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
                try (InputStream file = new URL(link).openStream()) {
                    TranscoderInput transIn = new TranscoderInput(file);
                    try {
                        transcoder.transcode(transIn, null);
                        Image img = SwingFXUtils.toFXImage(transcoder.getBufferedImage(), null);
                        LogoImage.setImage(img);
                    } catch (TranscoderException ex) {
                        System.out.println("Failed to read image from URL");
                    }
                }
                catch (IOException io) {
                    io.printStackTrace();
                }
            }
            else{
                try {
                    image = ImageIO.read(url);
                    Image imagen = SwingFXUtils.toFXImage(image, null);
                    LogoImage.setImage(imagen);
                } catch (IOException e) {
                    System.out.println("Failed to read image from URL");
                }
            }
        }
    }

    public class BufferedImageTranscoder extends ImageTranscoder {
        private BufferedImage img = null;
        @Override
        public BufferedImage createImage(int width, int height) {
            return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }
        @Override
        public void writeImage(BufferedImage img, TranscoderOutput to) throws TranscoderException {
            this.img = img;
        }
        public BufferedImage getBufferedImage() {
            return img;
        }
    }

}
