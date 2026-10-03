public class EquipModel{
    private Integer code;
    private String model;
    private String brand;
    private int fee;
    private Boolean disponibility = true;
    private int amount;

    public EquipModel(){

    }

    public boolean isDiponibility(){
        return disponibility;
    }

    public void setDisponibility(boolean disponibility){
        this.disponibility = disponibility;
    }

    public String getDisponibilityText() {
        return disponibility ? "Disponible" : "No disponible";
    }

    public void setCode(Integer code){
        if(code == null || code <= 0 ){
            throw new IllegalArgumentException(
                "El código es obligatorio y debe ser mayor que cero."
            );
        }

        this.code = code;
    }

    public void validar() {
        System.out.println("Código dentro de EquipModel.validar(): " + this.code);
        
        if (code == null || code <= 0) {
            throw new IllegalArgumentException(
                "El código es obligatorio y debe ser mayor que cero."
            );
        }

        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo es obligatorio.");
        }

        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca es obligatoria.");
        }
    }

    public Integer getCode(){
        return code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model){
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee){
        this.fee = fee;
    }

    public int getAmount(){
        return amount;
    }

    public boolean isDisponibility() {
        return disponibility;
    }
    
}