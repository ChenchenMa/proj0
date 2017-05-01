public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos=xP; yyPos=yP;xxVel=xV;yyVel=yV;mass=m;imgFileName=img;
    }
    public Planet(Planet p){
        xxPos=p.xxPos; yyPos=p.yyPos;xxVel=p.xxVel;yyVel=p.yyVel;mass=p.mass;imgFileName=p.imgFileName;
    }
    public double calcDistance(Planet Y){
        double dx= Y.xxPos-this.xxPos;
        double dy= Y.yyPos-this.yyPos;
        double r2= dx*dx+dy*dy;
        return Math.sqrt(r2);
    }
    public double calcForceExertedBy(Planet Y){
        double r=this.calcDistance(Y);
        double G=6.67 * Math.pow(10,-11);
        double F=G*this.mass*Y.mass/(r*r);
        return F;
    }
    public double calcForceExertedByX(Planet Y){
        double dx=Y.xxPos-this.xxPos;
        double r=this.calcDistance(Y);
        double F=this.calcForceExertedBy(Y);
        double Fx=F*dx/r;
        return Fx;
    }
    public double calcForceExertedByY(Planet Y){
        double dy=Y.yyPos-this.yyPos;
        double r=this.calcDistance(Y);
        double F=this.calcForceExertedBy(Y);
        double Fy=F*dy/r;
        return Fy;
    }
    public double calcNetForceExertedByX(Planet[] A){
        int i=0;
        double FNETx=0;
        while(i<A.length){
            if(this.equals(A[i])){i+=1;}
            else{
                FNETx+=this.calcForceExertedByX(A[i]);
                i+=1;
            }
        }
        return FNETx;
    }
    public double calcNetForceExertedByY(Planet[] A){
        int i=0;
        double FNETy=0;
        while(i<A.length){
            if(this.equals(A[i])){i+=1;}
            else{
                FNETy+=this.calcForceExertedByY(A[i]);
                i+=1;
            }
        }
        return FNETy;
    }
    public void update(double dt, double fX, double fY){
    double ax=fX/this.mass;
    double ay=fY/this.mass;
    this.xxVel+=ax*dt;
    this.yyVel+=ay*dt;
    this.xxPos+=dt*this.xxVel;
    this.yyPos+=dt*this.yyVel;
    }
    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,this.imgFileName);
    }
}
