package com.example.checkpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
public class Controller {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/camelize")
    public String getIndividualParams(
            @RequestParam String original,
            @RequestParam(required = false) boolean initialCap ) {
            String camel = toCamelCase(original,initialCap,new char[]{'_'});
        return String.format("%s", camel);
    }

    @GetMapping("/redact")
    public String redact(
            @RequestParam String original,
            @RequestParam(value="badWord") List<String> words
    ) {
        int total = 0;



        for (int i = 0; i<words.size(); i++){
           original = original.replaceAll(words.get(i),"*****");
        }

        return original;
    }

    @PostMapping("/encode")
    public String encode() {
        return "Hello from Spring!";
    }

    @PostMapping("s/<find>/<replacement>")
    public String camel() {
        return "Hello from Spring!";
    }

    public static String toCamelCase(String str, final boolean capitalizeFirstLetter, final char... delimiters) {
        str = str.toLowerCase();
        final int strLen = str.length();
        final int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        final Set<Integer> delimiterSet = generateDelimiterSet(delimiters);
        boolean capitalizeNext = false;
        if (capitalizeFirstLetter) {
            capitalizeNext = true;
        }
        for (int index = 0; index < strLen;) {
            final int codePoint = str.codePointAt(index);

            if (delimiterSet.contains(codePoint)) {
                capitalizeNext = true;
                if (outOffset == 0) {
                    capitalizeNext = false;
                }
                index += Character.charCount(codePoint);
            } else if (capitalizeNext || outOffset == 0 && capitalizeFirstLetter) {
                final int titleCaseCodePoint = Character.toTitleCase(codePoint);
                newCodePoints[outOffset++] = titleCaseCodePoint;
                index += Character.charCount(titleCaseCodePoint);
                capitalizeNext = false;
            } else {
                newCodePoints[outOffset++] = codePoint;
                index += Character.charCount(codePoint);
            }
        }
        if (outOffset != 0) {
            return new String(newCodePoints, 0, outOffset);
        }
        return str;}

    private static Set<Integer> generateDelimiterSet(final char[] delimiters) {
        final Set<Integer> delimiterHashSet = new HashSet<>();
        delimiterHashSet.add(Character.codePointAt(new char[]{' '}, 0));
        if (delimiters == null || delimiters.length == 0) {
            return delimiterHashSet;
        }

        for (int index = 0; index < delimiters.length; index++) {
            delimiterHashSet.add(Character.codePointAt(delimiters, index));
        }
        return delimiterHashSet;
    }
}





