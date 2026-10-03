public class CategoryEquip extends EquipModel {
    private static final String[] CATEGORIES = {
        "Computadora", "Impresora", "Proyector"
    };

    private String category;
    private String characteristic;

    public CategoryEquip(int opcionCategoria) {
        setCategory(opcionCategoria);
    }

    public static String[] getCategories() {
        return CATEGORIES.clone();
    }

    public void setCategory(int opcion) {
        if (opcion < 1 || opcion > CATEGORIES.length) {
            throw new IllegalArgumentException("Categoría inválida.");
        }

        this.category = CATEGORIES[opcion - 1];

        this.characteristic = null;
    }

    public String getCategory() {
        return category;
    }

    public void setCharacteristic(String characteristic) {
        if (characteristic == null || characteristic.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Las características son obligatorias."
            );
        }

        this.characteristic = characteristic.trim();
    }

    public String getCharacteristic() {
        return characteristic;
    }

    @Override
    public void validar() {
        super.validar();

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Debe seleccionar una categoría."
            );
        }

        if (characteristic == null || characteristic.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Las características son obligatorias."
            );
        }
    }
}
