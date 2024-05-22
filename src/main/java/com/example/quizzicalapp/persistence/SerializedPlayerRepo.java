package com.example.quizzicalapp.persistence;

import com.example.quizzicalapp.application.PlayerRepo;
import com.example.quizzicalapp.entity.Player;

import java.io.*;
import java.util.ArrayList;

public class SerializedPlayerRepo extends PlayerRepo {

    private static SerializedPlayerRepo exemplar;

    public static SerializedPlayerRepo singleton(){
        if(exemplar==null)
            exemplar = new SerializedPlayerRepo();
        return exemplar;
    }

    @Override
    public void save() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("/Users/ruwenlamm/glassfish5/glassfish/domains/domain1/config/serializedRepo.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this.players);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fos.close();
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }





    }
    @Override
    public void read() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/Users/ruwenlamm/glassfish5/glassfish/domains/domain1/config/serializedRepo.ser");
            ois = new ObjectInputStream(fis);
            this.players = (ArrayList<Player>) ois.readObject();

        } catch (IOException ex) {
            System.out.println("File does not exist!!");
        } catch (ClassNotFoundException e) {
            System.out.println("File does not exist");
        } finally {
            try {
                if (fis != null)
                    fis.close();
                if (ois != null)
                    ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
}
