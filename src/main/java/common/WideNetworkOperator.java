package common;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class WideNetworkOperator extends LocalNetworkOperator {
    @Inject
    @Named("ipCheckerURL")
    private String ipCheckerURL;

    public String findAndReturnIPAddress() {
        try {
            URL url = new URL(ipCheckerURL);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            ipAddress = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipAddress;
    }
}