public class Computer {
    private final String CPU;
    private final String RAM;
    private final String storage;
    private final String graphicsCard;
    private final boolean hasBluetooth;
    private final boolean hasWiFi;

    // ✅ Private Constructor only accessible by Builder
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.hasBluetooth = builder.hasBluetooth;
        this.hasWiFi = builder.hasWiFi;
    }

    // ✅ Static Nested Builder Class
    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;
        private String graphicsCard;
        private boolean hasBluetooth;
        private boolean hasWiFi;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Builder setBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        public Builder setWiFi(boolean hasWiFi) {
            this.hasWiFi = hasWiFi;
            return this;
        }

        // 🔨 Final step: build the actual Computer object
        public Computer build() {
            return new Computer(this);
        }
    }

    // ✅ For displaying result
    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", storage=" + storage +
                ", graphicsCard=" + graphicsCard + ", Bluetooth=" + hasBluetooth +
                ", WiFi=" + hasWiFi + "]";
    }

    public static void main(String[] args) {
        Computer gamingPC = new Computer.Builder()
                .setCPU("Intel i9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGraphicsCard("NVIDIA RTX 4080")
                .setBluetooth(true)
                .setWiFi(true)
                .build();

        // 🧑‍💻 Office Laptop
        Computer officeLaptop = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM("8GB")
                .setStorage("512GB SSD")
                .setWiFi(true)
                .build();

        System.out.println(gamingPC);
        System.out.println(officeLaptop);

    }
}
