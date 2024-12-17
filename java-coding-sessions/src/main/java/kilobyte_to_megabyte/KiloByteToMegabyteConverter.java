package kilobyte_to_megabyte;

public class KiloByteToMegabyteConverter {
    private int oneMegabyteToKilobyte;

    public void convertKilobyteToMegabyte(int kiloBytes) {

        if (kiloBytes < 0) {
            System.out.println("Invalid Value");
            return;
        }

        int megabyte = kiloBytes / oneMegabyteToKilobyte;
        int remainingKilobyte = kiloBytes % oneMegabyteToKilobyte;

        System.out.printf("%d KB = %d MB and %d KB%n", kiloBytes, megabyte, remainingKilobyte);
    }

    public void setOneMegabyteToKilobyte(int oneMegabyteToKilobyte) {
        this.oneMegabyteToKilobyte = oneMegabyteToKilobyte;
    }


}
