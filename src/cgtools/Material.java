package cgtools;

public interface Material {
    
    public double emission(Hit hit);
    public double albedo(Hit hit);
    public Ray scatteredRay(Hit hit);
}
