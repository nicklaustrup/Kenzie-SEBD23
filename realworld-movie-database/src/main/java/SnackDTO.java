import java.util.List;

public class SnackDTO {

    @com.fasterxml.jackson.annotation.JsonProperty("varieties")
    private Varieties varieties;
    @com.fasterxml.jackson.annotation.JsonProperty("ppu")
    private double ppu;
    @com.fasterxml.jackson.annotation.JsonProperty("name")
    private String name;
    @com.fasterxml.jackson.annotation.JsonProperty("type")
    private String type;
    @com.fasterxml.jackson.annotation.JsonProperty("id")
    private String id;

    public Varieties getVarieties() {
        return varieties;
    }

    public void setVarieties(Varieties varieties) {
        this.varieties = varieties;
    }

    public double getPpu() {
        return ppu;
    }

    public void setPpu(double ppu) {
        this.ppu = ppu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class Varieties {
        @com.fasterxml.jackson.annotation.JsonProperty("flavor")
        private List<Flavor> flavor;

        public List<Flavor> getFlavor() {
            return flavor;
        }

        public void setFlavor(List<Flavor> flavor) {
            this.flavor = flavor;
        }
    }

    public static class Flavor {
        @com.fasterxml.jackson.annotation.JsonProperty("type")
        private String type;
        @com.fasterxml.jackson.annotation.JsonProperty("id")
        private String id;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
