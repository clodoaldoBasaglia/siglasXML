/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siglaseng2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clodoaldo
 */
public class SiglasEng2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileReader fr = null;
        ArrayList<Proceeding> proList = new ArrayList<>();
        try {
            // TODO code application logic here
            File file = new File("complete.xml");
            fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String texto = "";
            int cont = 0;
            String juncao = " ";
            while ((texto = bf.readLine()) != null) {
                juncao = juncao + texto + "\n";
            }
            String[] split = juncao.split("\n\n");
            for (String string : split) {
                Proceeding p = new Proceeding();
                String replaceAll = string.replaceAll("\\<[^>]*>", "");
                String[] campos = replaceAll.split("\n");
                if (campos.length >= 10) {
                    p.setBooktittle(campos[1]);
                    p.setTitle(campos[2]);
                    p.setAno((campos[3].equalsIgnoreCase(" ")) ? Integer.MIN_VALUE : Integer.parseInt(campos[3]));
                    p.setMes(campos[4]);
                    p.setDias(campos[5]);
                    p.setLocation(campos[6]);
                    p.setPublisher(campos[7]);
                    p.setEdicao(campos[8]);
                    p.setIsbn(campos[9]);
                    proList.add(p);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SiglasEng2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SiglasEng2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(SiglasEng2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
