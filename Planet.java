public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private double gravitational_constant = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}
	public Planet(Planet p){

		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;

	}
	public double calcDistance(Planet p2){
		double dx = this.xxPos - p2.xxPos;
		double dy = this.yyPos - p2.yyPos;
		double r_power = dx * dx + dy * dy;
		return Math.sqrt(r_power);

	}
	public double calcForceExertedBy(Planet p3){
		double r = calcDistance(p3);
		double r_power = r * r;
		return gravitational_constant * this.mass * p3.mass / r_power;

	}
	public double calcForceExertedByX(Planet px){
		double dx = px.xxPos - this.xxPos;
		double force = calcForceExertedBy(px);
		double r = calcDistance(px);
		return force * dx / r;

	}
	public double calcForceExertedByY(Planet py){
		double dy = py.yyPos - this.yyPos;
		double force = calcForceExertedBy(py);
		double r = calcDistance(py);
		return force * dy / r;
	}
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double netForceX = 0;
		for(int i = 0; i < allPlanets.length; i++){
			if(!(this.equals(allPlanets[i]))){
			//if(allPlanets[i].mass != this.mass){
				netForceX += this.calcForceExertedByX(allPlanets[i]);
			}
		}
		return netForceX;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double netForceY = 0;
		for (int i = 0; i < allPlanets.length; i++){
			if(!(this.equals(allPlanets[i]))){
			//if(allPlanets[i].mass != this.mass){
				netForceY += this.calcForceExertedByY(allPlanets[i]);
			}
		}
		return netForceY;
	}

	public void update(double dt, double fX, double fY){
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		this.xxVel += dt * ax;
		this.yyVel += dt * ay;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw(){
		String filepath = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, filepath);
	}

}










	
