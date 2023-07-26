import java.util.*;

class DESencryption
{

    public static void display(int [] arr, int size)        //display array
    {
        for(int i=0;i<size;i++)
        {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static int[] LeftShift(int [] mapP10, int val)       //Left shift array
    {
        int size= 5;
        int[] left = new int[size];
        int[] right = new int[size];

        for(int i=0;i<size;i++)
        {
            left[i] = mapP10[i];
        }
        for(int i=0;i<size;i++)
        {
            right[i] = mapP10[i+size];
        }
        // System.out.println("Left : ");
        // display(left, size);
        // System.out.println("Right : ");
        // display(right, size);

        int [] leftShift = new int[size];
        int [] rightShift = new int[size];

        for(int i=val;i<size;i++)
        {
            leftShift[i-val] = left[i];
            rightShift[i-val] = right[i];
        }
        for(int i=0;i<val;i++)
        {
            leftShift[i+size-val] = left[i];
            rightShift[i+size-val] = right[i];
        }
        // System.out.println("Left shift : ");
        // display(leftShift, size);
        // System.out.println("Right shift: ");
        // display(rightShift, size);

        int [] shifted =  new int[mapP10.length] ;

        System.arraycopy(leftShift, 0, shifted, 0, leftShift.length);
        System.arraycopy(rightShift, 0, shifted, leftShift.length, rightShift.length);

        // display(shifted, shifted.length);
        return shifted;
    }

    public static int [] shrink10_8(int[] shiftedP10, int[] p8)       //shrink 10 bits into 8 bits "Returns KEY"
    {
        int[] mapP8 = new int[8];

        for(int i=0;i<p8.length;i++)
        {
            mapP8[i] = shiftedP10[p8[i]-1];
        }

        // System.out.println("P8 : ");
        // display(mapP8, mapP8.length);

        return mapP8;
    }

    // public static int [] sBoxMaping(int[][] s, int[] xormappedEP)
    // { 
    //     int [] value = new int[2];

    //     if(xormappedEP[0]==0 && xormappedEP[3]==0)
    //         value[0]=0;
    //     else if(xormappedEP[0]==0 && xormappedEP[3]==1)
    //         value[0]=1;
    //     else if(xormappedEP[1] == 0)
    //         value[0] = 2;
    //     else
    //         value[0]=3;

    //     if(xormappedEP[1]==0 && xormappedEP[2]==0)
    //         value[1]=0;
    //     else if(xormappedEP[1]==0 && xormappedEP[2]==1)
    //         value[1]=1;
    //     else if(xormappedEP[2] == 0)
    //         value[1] = 2;
    //     else
    //         value[1]=3;

    //     String [] sValue = ((s[value[0]][value[1]])+" ").split((""));
    //      int [] fvalue = new int[2];

    //     for (int i = 0; i < sValue.length; i++) {
    //         fvalue[i] = Integer.parseInt(sValue[i]);
    //     }

    //     return fvalue;
    // }

    public static int[] sBoxMaping(String[][] s, int[] xormappedEP)
    { 
        int [] value = new int[2];        

        if(xormappedEP[0]==0 && xormappedEP[3]==0)
            value[0]=0;
        else if(xormappedEP[0]==0 && xormappedEP[3]==1)
            value[0]=1;
        else if(xormappedEP[0]==1 && xormappedEP[3]==0)
            value[0] = 2;
        else
            value[0]=3;

        if(xormappedEP[1]==0 && xormappedEP[2]==0)
            value[1]=0;
        else if(xormappedEP[1]==0 && xormappedEP[2]==1)
            value[1]=1;
        else if(xormappedEP[1]==1 && xormappedEP[2]==0)
            value[1] = 2;
        else
            value[1]=3;


        // System.out.println(value[0]);
        // System.out.println(value[1]);

        String  str = (s[value[0]][value[1]]);
        String [] sValue = str.split("");

         int [] fvalue = new int[2];

        for (int i = 0; i < sValue.length; i++) {
            fvalue[i] = Integer.parseInt(sValue[i]);
            // System.out.println("i = "+fvalue[i]);
        }

        return fvalue;
    }

    public static int[] feistelStructure(int [] rightMapedIP ,int [] ep, int [] key1,String [][]s0, String [][]s1, int[] P4, int []leftMapedIP )
    {
        // left xor + s0s1 = rightmappedIP + Key + So + S1

        int [] mapEP = new int[8];

        for(int i=0;i<ep.length;i++)       // mapped EP with Right Maped IP (4-bit) {Expand}
        {
            mapEP[i] = rightMapedIP[ep[i]-1];
        }


        System.out.print("Mapped EP : ");
        display(mapEP, mapEP.length);

        int [] xorMapEP = new int[8];

        for(int i=0;i<xorMapEP.length;i++)       // XOR mapped EP and KEY-1
        {
            if(mapEP[i]==key1[i])
                xorMapEP[i] = 0;
            else
                xorMapEP[i] = 1;
            
        }
        System.out.print("XOR - Mapped EP : ");
        display(xorMapEP, xorMapEP.length);

        int [] leftXORmapEP = new int[4];
        int [] rightXORmapEP = new int[4];

        for(int i=0;i<leftXORmapEP.length;i++)       // Left XOR mapped  EP
        {
            leftXORmapEP[i] = xorMapEP[i];
        }

        for(int i=0;i<rightXORmapEP.length;i++)       // Right XOR mapped  EP
        {
            rightXORmapEP[i] = xorMapEP[i+4];
        }

         System.out.print("left XOR Mapped IP : ");
        display(leftXORmapEP, leftXORmapEP.length);

         System.out.print("Right XOR Mapped IP : ");
        display(rightXORmapEP, rightXORmapEP.length);

        System.out.print("S0 Value : ");
        int [] s0value = sBoxMaping(s0, leftXORmapEP); // maped Left XOR mapedEP with SO
        display(s0value, s0value.length);

        System.out.print("S1 Value : ");
        int [] s1value = sBoxMaping(s1, rightXORmapEP); // maped Right XOR mapedEP with S1
        display(s1value, s1value.length);

        int [] mergeS0S1 = new int[4];
        System.arraycopy(s0value, 0, mergeS0S1, 0, s0value.length); // merged s0 + s1
        System.arraycopy(s1value, 0, mergeS0S1, s0value.length, s1value.length);

        System.out.print("SO + S1 : ");
        display(mergeS0S1, mergeS0S1.length);

        int [] P4mapS0S1 = new int[4];  // map merged S0S1 with P4

        for(int i=0;i<P4mapS0S1.length;i++)
        {
            P4mapS0S1[i] = mergeS0S1[P4[i]-1];
        }

        System.out.print("Mapped P4 : ");
        display(P4mapS0S1, P4mapS0S1.length);

        int [] xorKeyS0S1 = new int[4];

        for(int i=0;i<xorKeyS0S1.length;i++)       // XOR left mapped IP and P4mapS0S1
        {
            if(P4mapS0S1[i]==leftMapedIP[i])
                xorKeyS0S1[i] = 0;
            else
                xorKeyS0S1[i] = 1;
            
        }

        System.out.print("XOR LeftmapedIP + SOS1 : ");
        display(xorKeyS0S1, xorKeyS0S1.length);

        return xorKeyS0S1;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int [] iv ={0,0,1,0,0,1,0,1,1,1};
        int [] p = {0,0,1,1,0,1,1,0};
        int [] p10 ={3,5,2,7,4,10,1,9,8,6};
        int [] p8 ={6,3,7,4,8,5,10,9};
        int [] ip = {2,6,3,1,4,8,5,7}; //remaing
        int [] ep = {4,1,2,3,2,3,4,1}; //remaing
        String [][] s0 = {
            {"01","00","11","10"},
            {"11","10","01","00"},
            {"00","10","01","11"},
            {"11","01","11","10"}
        };                          //remaing
         String [][] s1 = {
            {"00","01","10","11"},
            {"10","00","01","11"},
            {"11","00","01","00"},
            {"10","01","00","11"}
        };                          //remaing

        int [] P4 = {2,4,3,1}; // reamining

        int[] IPinverse = {4,1,3,5,7,2,8,6};  //remaining

        //declare required variables
        // int [] iv =new int[10];
        // int [] p10 =new int[10];
        // int [] p8 =new int[8];

        //input for IV
        // System.out.println("Enter Initial  Value : ");
        // for(int i=0;i<iv.length;i++)
        // {
        //     iv[i] = sc.nextInt();
        // }

        // //input for P10
        // System.out.println("Enter P10  Value : ");
        // for(int i=0;i<p10.length;i++)
        // {
        //     p10[i] = sc.nextInt();
        // }

        // //input for P8
        // System.out.println("Enter P8  Value : ");
        // for(int i=0;i<p8.length;i++)
        // {
        //     p8[i] = sc.nextInt();
        // }

        int [] mapP10 = new int[10];

        for(int i=0;i<p10.length;i++)       // mapped p10 with iv(K)
        {
            mapP10[i] = iv[p10[i]-1];
        }

        // System.out.println("P10 : ");
        // display(mapP10, mapP10.length);

        // Left shift array derived ------------------------------------------------- KEY 1
        int val = 1;
        int [] shiftedP10 = LeftShift(mapP10, val);
        
        // display(shiftedP10, shiftedP10.length);---------

        //Shift shiftedP10-10 bits into 8bits

        int [] key1 = shrink10_8(shiftedP10, p8);
        System.out.print("KEY 1 : ");
        display(key1, key1.length);

        // Left shift key1 --------------------------------------------- KEY 2
         val = 2;
        shiftedP10 = LeftShift(shiftedP10, val);

        //Shift shiftedP10-10 bits into 8bits

        int [] key2 = shrink10_8(shiftedP10, p8);
        System.out.print("KEY 2 : ");
        display(key2, key2.length);

        int [] mapIP = new int[8];

        for(int i=0;i<ip.length;i++)       // mapped ip with P - plain txt
        {
            mapIP[i] = p[ip[i]-1];
        }
        System.out.print("Mapped IP : ");
        display(mapIP, mapIP.length);

        int [] leftMapedIP = new int[4];
        int [] rightMapedIP = new int[4];

        for(int i=0;i<leftMapedIP.length;i++)       // Left mapped ip -4 bits
        {
            leftMapedIP[i] = mapIP[i];
        }

        for(int i=0;i<leftMapedIP.length;i++)       // Right mapped ip -4 bits
        {
            rightMapedIP[i] = mapIP[i+4];
        }

         System.out.print("left Mapped IP : ");
        display(leftMapedIP, leftMapedIP.length);

         System.out.print("Right Mapped IP : ");
        display(rightMapedIP, rightMapedIP.length);

        System.out.println("------------------------Round 1-------------------------");
        int [] xorKey1S0S1 =feistelStructure(rightMapedIP,ep, key1, s0, s1,P4, leftMapedIP ); // round 1 

        int [] swappedXORleftS0S1 = new int[8];         //// swapped xorKey1S0S1 + rightMapedIP
        for(int i=0;i<swappedXORleftS0S1.length;i++)
        {
            if(i<4)
                swappedXORleftS0S1[i] = rightMapedIP[i];
            else
                swappedXORleftS0S1[i] = xorKey1S0S1[i-4];
        }

        System.out.print("Swapped  XOR LeftmapedIP and RightmapedIP : ");
        display(swappedXORleftS0S1, swappedXORleftS0S1.length);

        int [] leftSwappedR1 = new int [4];
        int [] rightSwappedR1 = new int [4];

         for(int i=0;i<leftSwappedR1.length;i++)       // LeftSwapped mapped ip -4 bits
        {
            leftSwappedR1[i] = swappedXORleftS0S1[i];
        }

        for(int i=0;i<rightSwappedR1.length;i++)       // RightSwapped mapped ip -4 bits
        {
            rightSwappedR1[i] = swappedXORleftS0S1[i+4];
        }

        System.out.print("----------------------leftSwappedR1 : ");
        display(leftSwappedR1, leftSwappedR1.length);
        System.out.print("----------------------rightSwappedR1 : ");
        display(rightSwappedR1, rightSwappedR1.length);

        System.out.println("------------------------Round 2-------------------------");
        int [] xorKey2S0S1 = feistelStructure(rightSwappedR1,ep, key2, s0, s1,P4, leftSwappedR1 ); // round 2

        int [] mergeXORkey2rightSwappedR1 = new int [8];    // Concatenate xor key2 S0S1 + right swapped R1

        for(int i=0;i<mergeXORkey2rightSwappedR1.length;i++)
        {
            if(i<4)
                mergeXORkey2rightSwappedR1[i] = xorKey2S0S1[i];
            else
                mergeXORkey2rightSwappedR1[i] = rightSwappedR1[i-4];
        }

        System.out.print("----------------------merge XORkey2S0S1 + rightSwappedR1 : ");
        display(mergeXORkey2rightSwappedR1, mergeXORkey2rightSwappedR1.length);

        // Last map merge XORkey2S0S1 + rightSwappedR1 with IP-1(inverse)

        int [] mappedIPinverse = new int[8];

        for(int i=0;i<mappedIPinverse.length;i++)
        {
            mappedIPinverse[i] = mergeXORkey2rightSwappedR1[IPinverse[i]-1];
        }

        System.out.print("----------------------IP inverse: ");
        display(mappedIPinverse, mappedIPinverse.length);

        int [] ciphertext = new int [8];
        System.arraycopy(mappedIPinverse,0,ciphertext,0,mappedIPinverse.length);

        System.out.print("----------------------Cipher Tex: ");
        display(ciphertext,ciphertext.length);

        //-----------------------------------Encrption Ends------------------------------------

        //------------------------------------------Decryption Starts--------------------------------------------
        // startining with cipher text

        System.out.println("------------------------------Decryption Starts----------------------------");
        int [] mapIPCipherText = new int[8];

        for(int i=0;i<ciphertext.length;i++)       // mapped ip with P - plain txt
        {
            mapIPCipherText[i] = ciphertext[ip[i]-1];
        }
        System.out.print("Mapped IP  with Cipher text : ");
        display(mapIPCipherText, mapIPCipherText.length);

        int [] leftCipherMapedIP = new int[4];
        int [] rightCipherMapedIP = new int[4];

        for(int i=0;i<leftCipherMapedIP.length;i++)       // Left mapped ip and Cipher -4 bits
        {
            leftCipherMapedIP[i] = mapIPCipherText[i];
        }

        for(int i=0;i<leftCipherMapedIP.length;i++)       // Right mapped ip and Cipher -4 bits
        {
            rightCipherMapedIP[i] = mapIPCipherText[i+4];
        }

         System.out.print("left Mapped IP Inverse + Cipher : ");
        display(leftCipherMapedIP, leftCipherMapedIP.length);

         System.out.print("Right Mapped IP Inverse + Cipher : ");
        display(rightCipherMapedIP, rightCipherMapedIP.length);

         System.out.println("------------------------Decrypt - Round 1-------------------------");
        int [] xorKey2S0S1Cipher =feistelStructure(rightCipherMapedIP,ep, key2, s0, s1,P4, leftCipherMapedIP ); // round 1  Cipher

        int [] swappedXORleftS0S1Cipher = new int[8];         //// swapped xorKey1S0S1 + rightMapedIP
        for(int i=0;i<swappedXORleftS0S1Cipher.length;i++)
        {
            if(i<4)
                swappedXORleftS0S1Cipher[i] = rightCipherMapedIP[i];
            else
                swappedXORleftS0S1Cipher[i] = xorKey2S0S1Cipher[i-4];
        }

        System.out.print("Cipher - Swapped  XOR LeftmapedIP and RightmapedIP : ");
        display(swappedXORleftS0S1Cipher, swappedXORleftS0S1Cipher.length);

        int [] leftSwappedR1Cipher = new int [4];
        int [] rightSwappedR1Cipher = new int [4];

         for(int i=0;i<leftSwappedR1Cipher.length;i++)       // LeftSwapped mapped ip -4 bits
        {
            leftSwappedR1Cipher[i] = swappedXORleftS0S1Cipher[i];
        }

        for(int i=0;i<rightSwappedR1Cipher.length;i++)       // RightSwapped mapped ip -4 bits
        {
            rightSwappedR1Cipher[i] = swappedXORleftS0S1Cipher[i+4];
        }

        System.out.print("Cipher  ------------leftSwappedR1 : ");
        display(leftSwappedR1Cipher, leftSwappedR1Cipher.length);
        System.out.print("Cipher  ------------rightSwappedR1 : ");
        display(rightSwappedR1Cipher, rightSwappedR1Cipher.length);

        System.out.println("-----------------------Decrypt - Round 2-------------------------");
        int [] xorKey1S0S1Cipher = feistelStructure(rightSwappedR1Cipher,ep, key1, s0, s1,P4, leftSwappedR1Cipher ); // round 2

        int [] mergeXORkey1rightSwappedR1Cipher = new int [8];    // Concatenate xor key2 S0S1 + right swapped R1

        for(int i=0;i<mergeXORkey1rightSwappedR1Cipher.length;i++)
        {
            if(i<4)
                mergeXORkey1rightSwappedR1Cipher[i] = xorKey1S0S1Cipher[i];
            else
                mergeXORkey1rightSwappedR1Cipher[i] = rightSwappedR1Cipher[i-4];
        }

        System.out.print("-------Cipher ----------merge XORkey1S0S1 + rightSwappedR1 : ");
        display(mergeXORkey1rightSwappedR1Cipher, mergeXORkey1rightSwappedR1Cipher.length);

        int [] mappedIPInverseCipher = new int[8];

        for(int i=0;i<mappedIPInverseCipher.length;i++)
        {
            mappedIPInverseCipher[i] = mergeXORkey1rightSwappedR1Cipher[IPinverse[i]-1];
        }

        System.out.print("----------------------IP inverse: ");
        display(mappedIPInverseCipher, mappedIPInverseCipher.length);

        int [] plainText = new int [8];
        System.arraycopy(mappedIPInverseCipher,0,plainText,0,mappedIPInverseCipher.length);

        System.out.print("----------------------Plain Tex: ");
        display(plainText,plainText.length);
    }
}