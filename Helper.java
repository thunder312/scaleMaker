import java.nio.charset.Charset;

public class Helper {
    public static String convertToCharset(String input, Charset charset) {
        byte[] bytes = input.getBytes(charset);
        String result = new String(bytes, charset);

        //ByteBuffer buffer = StandardCharsets.UTF_8.encode(input); 
        //String result = StandardCharsets.UTF_8.decode(buffer).toString();
        return result;
    }
}
