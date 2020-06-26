package com.aboujihad.vlsmcalc;

import java.util.ArrayList;
import java.util.LinkedList;



public class IP {

    private int[] IP_DEC = new int[4];
    private int[] MASK_DEC = new int[4];

    public int[] getIP_DEC() {
        return IP_DEC;
    }

    public void setIP_DEC(int[] IP_DEC) {
        this.IP_DEC = IP_DEC;
    }

    public int[] getMASK_DEC() {
        return MASK_DEC;
    }

    public void setMASK_DEC(int[] MASK_DEC) {
        this.MASK_DEC = MASK_DEC;
    }

    public int[] convetrToOctBin(int octDec) {
        int[] tab_Bin = {0, 0, 0, 0, 0, 0, 0, 0};
        int reste = 0;

        for (int i = 0; i < tab_Bin.length; i++) {
            reste = octDec % 2;
            octDec = octDec / 2;
            tab_Bin[i] = reste;

        }
        tab_Bin = this.inverse(tab_Bin);
        return tab_Bin;
    }

    public int convertToOctDec(int[] octBin) {
        int octDec = 0;
        for (int i = 0; i < octBin.length; i++) {
            octDec = octDec + Calculer.puissance(2, 7 - i) * octBin[i];
        }

        return octDec;
    }

    public int[][] convertToIpBin(int[] ipDec) {
        int[][] ipBin = new int[4][8];
        for (int i = 0; i < 4; i++) {
            ipBin[i] = this.convetrToOctBin(ipDec[i]);
        }

        return ipBin;
    }

    public int[] convertToIpDec(int[][] ipBin) {
        int[] ipDec = new int[4];
        for (int i = 0; i < ipDec.length; i++) {
            ipDec[i] = this.convertToOctDec(ipBin[i]);
        }

        return ipDec;
    }

    public int[] concat(int[][] ipBin) {
        int[] ip32Bits = new int[32];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                ip32Bits[j + 8 * i] = ipBin[i][j];
            }
        }

        return ip32Bits;
    }

    public int[][] deConcat(int[] ip32bit) {
        int[][] ipBin = new int[4][8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                ipBin[i][j] = ip32bit[j + (8 * i)];
            }
        }

        return ipBin;
    }

    public int[] inverse(int[] octBin) {
        int[] octBinInv = new int[8];
        for (int i = 7; i != -1; i--) {
            octBinInv[i] = octBin[7 - i];
        }

        return octBinInv;
    }

    public int[] getNetId(int[] ipDec, int[] maskDec) {
        int[] netId = new int[4];
        int[][] ipBin = this.convertToIpBin(ipDec);
        int[][] maskBin = this.convertToIpBin(maskDec);

        int[] ipBin32bit = this.concat(ipBin);
        int[] maskBin32bit = this.concat(maskBin);

        int[] netIdBin32bit = Calculer.produitTab(ipBin32bit, maskBin32bit);

        int[][] netIdBin = this.deConcat(netIdBin32bit);

        netId = this.convertToIpDec(netIdBin);

        return netId;
    }

    public int[] getFirstAddress(int[] ipDec, int[] maskDec) {
        int[] firstAddr = new int[4];
        int[] netIdDec = this.getNetId(ipDec, maskDec);
        // int[][] netIdBin = this.convertToIpBin(netIdDec);

        //  netIdBin[3][7] = 1;
        //  firstAddr = this.convertToIpDec(netIdBin);
        firstAddr = netIdDec;
        firstAddr[3] = firstAddr[3] + 1;

        return firstAddr;
    }

    public int[] getLastAddress(int[] ipDec, int[] maskDec) {
        int[] lastAddr = new int[4];

        int[] netIdBin32Bit = this.concat(this.convertToIpBin(this.getNetId(ipDec, maskDec)));
        int nbrBitInUserId = this.getNbrBitInUserId(maskDec);

        for (int i = (32 - nbrBitInUserId); i < 31; i++) {
            netIdBin32Bit[i] = 1;
        }
        netIdBin32Bit[31] = 0;

        lastAddr = this.convertToIpDec(this.deConcat(netIdBin32Bit));

        return lastAddr;
    }

    public int[] getBroadCastAddress(int[] ipDec, int[] maskDec) {
        int[] broadCastAddr = new int[4];

        broadCastAddr=this.getLastAddress(ipDec, maskDec);
        broadCastAddr[3]++;

        return broadCastAddr;
    }



    public int getNbrOfUseAddr(int[] maskDec) {
        int NbrOfUseAddr = 1;
        int NbrBitInUserId = this.getNbrBitInUserId(maskDec);

        NbrOfUseAddr = Calculer.puissance(2, NbrBitInUserId) - 2;

        return NbrOfUseAddr;
    }

    public int getNbrBitInUserId(int[] maskDec) {
        int NbrBitInUserId = 0;

        int[][] maskBin = this.convertToIpBin(maskDec);
        int[] maskBin32bit = this.concat(maskBin);

        for (int i = 0; i < maskBin32bit.length; i++) {
            if (maskBin32bit[i] == 0) {
                NbrBitInUserId++;
            }
        }
        return NbrBitInUserId;

    }

    public int[] getNewMask(int[] ipDec, int[] maskDec, int nbr_de_sous_res) {
        int[] NewMask = new int[4];

        int[] netId = this.getNetId(ipDec, maskDec);
        int nbrBitEmprunt = Calculer.getNbrBitEmprunt(nbr_de_sous_res);
        int NbrBitInUserId = this.getNbrBitInUserId(maskDec);

        // int newNbrBitInUserId = NbrBitInUserId - nbrBitEmprunt;
        int[][] maskBin = this.convertToIpBin(maskDec);
        int[] maskBin32bit = this.concat(maskBin);

        for (int i = 0; i < maskBin32bit.length; i++) {
            if (maskBin32bit[i] == 0 && nbrBitEmprunt != 0) {
                maskBin32bit[i] = 1;
                nbrBitEmprunt--;

            }

        }

        int[][] NewMaskBin = this.deConcat(maskBin32bit);

        NewMask = this.convertToIpDec(NewMaskBin);

        return NewMask;
    }

    public int[] getNewVlsmMask(int nbr_de_machines) {
        int[] NewMask = new int[4];
        //   int NbrBitInUserId = this.getNbrBitInUserId(maskDec);
        int nbrBitEmpruntINUserId=Calculer.getNbrBitEmpruntInUserId(nbr_de_machines);
        //     int nbrBitEmprunt = NbrBitInUserId - nbrBitEmpruntINUserId;

        int[][] maskBin = this.convertToIpBin(NewMask);
        int[] maskBin32bit = this.concat(maskBin);

        for (int i = 0; i < 32-nbrBitEmpruntINUserId; i++) {

            maskBin32bit[i] = 1;




        }

        int[][] NewMaskBin = this.deConcat(maskBin32bit);

        NewMask = this.convertToIpDec(NewMaskBin);


        return NewMask;
    }

    public int getNbrOfSubnet(int nbrBitEmprunt) {

        return Calculer.puissance(2, nbrBitEmprunt);
    }

    public int getNbrOfSubnet(int[] mask,int nbrDeMachines) {


        return Calculer.puissance(2, this.getNbrBitEmpruntInNetId(mask, Calculer.getNbrBitEmpruntInUserId(nbrDeMachines)));
    }



    public int getNbrSubnetInit(int[] oldMask,int[] newMask) {
        int NbrSubnetInit=0;
        int nbrBitEmprunt=0;
        for(int i=0;i<32;i++){
            if(this.concat(this.convertToIpBin(oldMask)) != this.concat(this.convertToIpBin(newMask))){
                nbrBitEmprunt++;
            }
        }
        NbrSubnetInit=Calculer.puissance(2, nbrBitEmprunt);

        return NbrSubnetInit;
    }

    public int getHopeDec(int NbrBitInUserId) {
        int hopeDec;

        hopeDec = Calculer.puissance(2, NbrBitInUserId);

        return hopeDec;
    }

    public int[] getHopeBin32bit(int NbrBitInUserId) {
        int[] hopeBin32bit = new int[32];

        for (int i = 0; i < hopeBin32bit.length; i++) {
            hopeBin32bit[i] = 0;
        }

        hopeBin32bit[31 - NbrBitInUserId] = 1;

        return hopeBin32bit;
    }

    public ArrayList getAllSubnet(int[] ipDec, int[] maskDec, int nbr_de_sous_res) {
        ArrayList<int[]> ipNet = new ArrayList();

        int[] newMask = this.getNewMask(ipDec, maskDec, nbr_de_sous_res);
        int nbrOfSubnet = Calculer.puissance(2, Calculer.getNbrBitEmprunt(nbr_de_sous_res));

        int[] firstSubnet = this.getNetId(ipDec, newMask);
        int[] firstSubnet32bit = this.concat(this.convertToIpBin(firstSubnet));
        int[] hopeBin32bit = this.getHopeBin32bit(this.getNbrBitInUserId(newMask));

        ipNet.add(firstSubnet32bit);

        for (int i = 1; i < nbrOfSubnet; i++) {
//            ipNet.add(firstSubnet32bit);
//            int[] nextSubnet=Calculer.SommeTab(firstSubnet32bit, hopeBin32bit);
//                    firstSubnet32bit=nextSubnet;

            ipNet.add(Calculer.SommeTab(ipNet.get(i - 1), hopeBin32bit));
        }

        for (int i = 0; i < ipNet.size(); i++) {
            ipNet.set(i, this.convertToIpDec(this.deConcat(ipNet.get(i))));
        }

        return ipNet;
    }

    public ArrayList getAllVlsmSubnetMask(int[] val_col) {
        ArrayList<int[]> VlsmSubNetMask = new ArrayList();

        for(int i=0;i<val_col.length;i++){
            VlsmSubNetMask.add(this.getNewVlsmMask(val_col[i]));
        }

        return VlsmSubNetMask;
    }

    public ArrayList getAllVlsmSubNet(int[] ipDec,int[] maskDec,int[] val_col){
        //  ArrayList<int[]> VlsmSubNet = new ArrayList();
        //  LinkedList listSubnetTemp= new LinkedList();
        ArrayList<int[]> ipNet = new ArrayList();
        ArrayList<int[]> VlsmSubNetMask=this.getAllVlsmSubnetMask(val_col);

        //    ipNet=this.getAllSubnet(ipDec, maskDec, this.getNbrOfSubnet(maskDec, val_col[0]));

        int[] firstSubnet = this.getNetId(ipDec, VlsmSubNetMask.get(0));
        int[] firstSubnet32bit = this.concat(this.convertToIpBin(firstSubnet));

        ipNet.add(firstSubnet32bit);
        int[] hopeBin32bit = this.getHopeBin32bit(this.getNbrBitInUserId(VlsmSubNetMask.get(0)));

        for(int i=1;i<val_col.length;i++){
            if(val_col[i]==val_col[i-1]){
                ipNet.add(Calculer.SommeTab(ipNet.get(i - 1), hopeBin32bit));
            }else{
                ipNet.add(Calculer.SommeTab(ipNet.get(i - 1), hopeBin32bit));
                hopeBin32bit=this.getHopeBin32bit(this.getNbrBitInUserId(VlsmSubNetMask.get(i)));
            }
        }

        for (int i = 0; i < ipNet.size(); i++) {
            ipNet.set(i, this.convertToIpDec(this.deConcat(ipNet.get(i))));
        }



        return ipNet;
    }

    public int getNbrBitEmpruntInNetId(int[] mask, int nbrBitEmpruntInUserId) {
        int NbrBitEmpruntInNetId = 0;

        NbrBitEmpruntInNetId = this.getNbrBitInUserId(mask) - nbrBitEmpruntInUserId;

        return NbrBitEmpruntInNetId;
    }

    public String getIptoString(int[] ipDec) {
        String ipAddStr = ipDec[0] + "." + ipDec[1] + "." + ipDec[2] + "." + ipDec[3];

        return ipAddStr;
    }

    public Boolean checkMaskError(int[] mask){
        Boolean maskError=false;
        int k=0;
        int[][] maskBin = this.convertToIpBin(mask);
        int[] maskBin32bit = this.concat(maskBin);

        if(maskBin32bit[0]==0){
            maskError=true;
        }else {
            for(int i=1;i<maskBin32bit.length-1;i++){
                if(maskBin32bit[i]==0){
                    k=i;
                }
                break;

            }
            for(int i=k+1;i<maskBin32bit.length-1;i++){
                if(maskBin32bit[i]==1){
                    maskError=true;
                }
                break;
            }
        }

        return maskError;
    }

    public int getSizeOfMainSubnet(int[] mask){
        return Calculer.puissance(2, this.getNbrBitInUserId(mask));
    }

    public int getSizeOfAllSubnets(int[] MachinesInSubNets){
        int SizeOfAllSubnets = 0;

        for(int i=0;i<MachinesInSubNets.length;i++){
            SizeOfAllSubnets = Calculer.puissance(2, Calculer.getNbrBitEmpruntInUserId(MachinesInSubNets[i])) + SizeOfAllSubnets ;
        }


        return SizeOfAllSubnets;
    }

}
