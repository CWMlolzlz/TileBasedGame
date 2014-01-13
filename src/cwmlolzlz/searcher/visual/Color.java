package cwmlolzlz.searcher.visual;

public class Color {
	
	private double r, g, b, a;

	public Color(double r,double g, double b, double a){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public Color(double r,double g, double b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 1;
	}
	
	public void setOpacity(double val){
		this.a = val;
	}
	
	public void setRed(double red){this.r = red;}
	public void setGreen(double green){this.g = green;}
	public void setBlue(double blue){this.b = blue;}
	public void setAlpha(double alpha){this.a = alpha;}
	
	public double red(){return r;}
	public double green(){return g;}
	public double blue(){return b;}
	public double alpha(){return a;}
	
	
}
