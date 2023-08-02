
public class AESencryption {

    public static void display(int [] arr, String name)
    {
        System.out.print(name+": ");

        for(int i=0;i<arr.length;i++)
        {
            System.out.print("\t"+arr[i]);
        }
        System.out.println();
    }

    public static int[] rotNib(int [] arr)              //swaps the array
    {
        int [] rotnib = new int[8];
        for(int i=0;i<arr.length;i++)
        {
            if(i<4)
            rotnib[i] = arr[i+4];
            else
            rotnib[i] = arr[i-4];
        }
        return rotnib;
    }

    public static void main(String[] args)
    {
        int [] key = {0,1,0,0,1,0,1,0,1,1,1,1,0,1,0,1};
        int [] RConst = {1,0,0,0,0,0,0,0};
        int []w0 = new int[8];
        int []w1 = new int[8];

        for(int i=0;i<w0.length;i++)        // W0
        {
            w0[i] = key[i];
        }

        for(int i=0;i<w1.length;i++)        // W1
        {
            w1[i] = key[i+8];
        }

        display(w0,"W0");
        display(w1,"W1");

        int [] rotNib = rotNib(w1);
        display(rotNib, "Rotate Nibble - Swap W1");
    }
    
}
