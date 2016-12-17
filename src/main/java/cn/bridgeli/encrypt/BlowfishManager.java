package cn.bridgeli.encrypt;

public enum BlowfishManager {

    BRIDGELI_CN("bridgeli_cn!@#$abc123_");

    private BlowfishManager(String secret) {
        this.blowfish = new Blowfish(secret);
    }

    private Blowfish blowfish;

    public Blowfish getBlowfish() {
        return blowfish;
    }

    /**
     * 解密
     * @param sCipherText
     * @return
     */
    public String decryptString(String sCipherText){
        return this.getBlowfish().decryptString(sCipherText);
    }

    /**
     * 加密
     * @param sPlainText
     * @return
     */
    public String encryptString(String sPlainText){
        return this.getBlowfish().encryptString(sPlainText);
    }

    public static void main(String[] args) {
        String encryptString = BlowfishManager.BRIDGELI_CN.encryptString(10 + "");
        System.out.println(encryptString);
        String decryptString = BlowfishManager.BRIDGELI_CN.decryptString(encryptString);
        System.out.println(decryptString);
    }
}
