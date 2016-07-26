package analizator;

public class NePostojiException extends Exception
{
    private String poruka;

    NePostojiException(String por)
    {
	this.poruka = por;
    }

    public void setPoruka(String por)
    {
		this.poruka = por;
    }

    public String getPoruka()
    {
		return this.poruka;
    }
}