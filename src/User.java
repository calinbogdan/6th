public class User {
    private int privateKey;

    public void setPrivateKey(int privateKey) {
        this.privateKey = privateKey;
    }

    public int getY(int primitiveRoot, int publicKey) {
        return (int) Math.pow(primitiveRoot, privateKey) % publicKey;
    }

    public int getK(int theOtherY, int publicKey) {
        return (int) Math.pow(theOtherY, privateKey) % publicKey;
    }

}
