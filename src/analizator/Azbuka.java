package analizator;

public class Azbuka
{
    private String slovo;
    private float verovatnoca;
    private int brojPojavljivanja = 0;

    public void incBrojPojavljivanja()
    {
        brojPojavljivanja++;
    }

    public int getBrojPojavljivanja()
    {
        return brojPojavljivanja;
    }

    public String getSlovo()
    {
        return slovo;
    }

    public void setSlovo(String s)
    {
        slovo = s;
    }

    public float getVerovatnoca()
    {
        return verovatnoca;
    }

    public void setVerovatnoca(float v)
    {
        verovatnoca = v;
    }

    public void printAzbuka()
    {
        System.out.println(getSlovo() + "   " + getVerovatnoca());
    }
}
