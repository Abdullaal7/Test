package eg.gov.moj.election.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class OtpRandomGenerator {

    List<Integer> OtpList = null;
    Integer i = 0;

    public OtpRandomGenerator() {
        this.OtpList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            OtpList.add(i);
        }
        Collections.shuffle(OtpList);
    }

    Integer nextOtp() {
        Integer integer = OtpList.get(i++);
        while (integer < 9999) {
            integer = OtpList.get(i++);
        }
        return integer;
    }

    public List<Integer> getOtpValues(Integer size) {
        List<Integer> otpList = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            otpList.add(nextOtp());
        }
        return otpList;
    }

    public HashSet<Integer> getSecureOtpRandom(Integer size) {
        try {
            SecureRandom randomGenerator = SecureRandom.getInstance("SHA1PRNG");
            HashSet<Integer> set = new HashSet<>();
            while (set.size() < size) {
                Integer nextItem = randomGenerator.nextInt(99999);
                while (nextItem < 9999) {
                    nextItem = randomGenerator.nextInt(99999);
                }
                set.add(nextItem);
            }
            return set;
        } catch (NoSuchAlgorithmException nsae) {
            // Forward to handler
        }
        return null;
    }


}
