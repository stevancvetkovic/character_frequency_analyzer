package analizator;

public class Main
{
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                Prikaz p = new Prikaz();
                p.setLocationRelativeTo(null);
                p.setVisible(true);
            }
        });
    }
}