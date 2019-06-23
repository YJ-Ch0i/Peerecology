package Api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.opencsv.CSVReader;



public class SchoolFinder {
   
    public static ArrayList<Item> OpenCSVSearch(String schoolNm,String uploadPath) {
          ArrayList<Item> resultlist = new ArrayList<Item>();
          String SchoolId=null;
          String SchoolRdnmadr=null;
          int sch_name_index=-1;
          int sch_id_index=-1;
          int sch_rdnmadr_index=-1;

           try{
            InputStreamReader is = new InputStreamReader(new FileInputStream(uploadPath+"\\초중등 정리.csv"), "EUC-KR");
            CSVReader reader = new CSVReader(is);
            List<String[]> list = reader.readAll();
            ArrayList<Item> arr = new ArrayList<>();
            
            int i=0;
            for(String[] str : list){
               Item entity = new Item();
               
               if(i==0) {
                  int j=0;
                   for(String temp : str) {
                      if(temp.equals("학교명")){sch_name_index=j;}
                      if(temp.equals("학교ID")){sch_id_index=j;}
                      if(temp.equals("소재지도로명주소")){sch_rdnmadr_index=j;}
                      j++;
                   }
                }
                else {
                entity.setSchoolId(str[sch_id_index]);
                entity.setSchoolname(str[sch_name_index]);
                entity.setRdnmadr(str[sch_rdnmadr_index]);
                arr.add(entity);
                }
               i++;
            }
            
            for(Item entity : arr){
                if(entity.getSchoolname().equals(schoolNm)) {
                   Item item = new Item();
                   item.setSchoolId(entity.getSchoolId());
                 item.setRdnmadr(entity.getRdnmadr());
                 resultlist.add(item);
                }
            }
            
           }catch(Exception e){
               e.printStackTrace();
           }
           return resultlist;
       }

   public static String xmlDownload(String schoolNm) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/elesch-mskul-lc-std"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=p2hOgfRTM0OmpDYbjg9X5geZlvl8tO6YWBDXU3EZG53xEHcK6GoMr8z5SxoIA9DIkAo1be%2F5ZagqEMnVR8EULw%3D%3D"); /*Service Key*/
        
        String key = "schoolNm";

        
        urlBuilder.append("&" + URLEncoder.encode(key,"UTF-8") + "=" + URLEncoder.encode(schoolNm, "UTF-8")); 
        
        
        URL url = new URL(urlBuilder.toString()); 
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line; 
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
      return sb.toString();
    }
   
   public ArrayList<Item> getItemList(String schoolNm) throws IOException, ParserConfigurationException, SAXException{
      ArrayList<Item> list = new ArrayList<Item>();
      String xml = SchoolFinder.xmlDownload(schoolNm);
      
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      
      Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
      
      NodeList nodeList = doc.getElementsByTagName("item");
      for(int i=0; i<nodeList.getLength(); i++) {
         Item item = new Item();
         Node node = nodeList.item(i);
         
         Element e = (Element)node;
         item.setSchoolId(e.getElementsByTagName("schoolId").item(0).getTextContent());
         item.setRdnmadr(e.getElementsByTagName("rdnmadr").item(0).getTextContent());

         list.add(item);
      }
      
      System.out.println(doc);
      
      return list;
   }
}