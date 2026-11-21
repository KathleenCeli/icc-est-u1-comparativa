public class Results {
    private String funcionNombre;
    private long duracion;
    private String sample;

    public Results() {
    }

    public Results(String funcionNombre, long duracion, String sample) {
        this.funcionNombre = funcionNombre;
        this.duracion = duracion;
        this.sample = sample;
    }

    public String getFuncionNombre() {
        return funcionNombre;
    }

    public void setFuncionNombre(String funcionNombre) {
        this.funcionNombre = funcionNombre;
    }

    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    @Override
    public String toString() {  
        double segundos = duracion / 1_000_000.0;
        return " Results" +
                ", funcionNombre: " + funcionNombre + ", duracion: " + segundos + " ms, sample: " 
                + sample;
    }


}
