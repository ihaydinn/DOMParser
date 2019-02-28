package com.ismailhakkiaydin.domparser;

import android.content.res.Resources;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources resources = this.getResources();
        InputStream stream = resources.openRawResource(R.raw.test);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
            Document document =db.parse(stream);
            document.getDocumentElement().normalize();
            NodeList nodes = document.getElementsByTagName("point");
            String str=" ";
            for (int i=0; i<=nodes.getLength()-1; i++){
                Node n = nodes.item(i);
                str += String.format("Point: %d \n\r",i);
                for (int j=0; j<n.getChildNodes().getLength()-1; j++){
                    Node child = n.getChildNodes().item(j);
                    str += String.format("%s = %s \n\r",child.getNodeName(),child.getTextContent());
                }
                str += "-------------------------------\n\r";

                TextView textView = new TextView(this);
                textView.setText(str);
                this.setContentView(textView);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }
}
