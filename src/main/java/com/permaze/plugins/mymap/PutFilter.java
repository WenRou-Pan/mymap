package com.permaze.plugins.mymap;

import com.janetfilter.core.commons.DebugInfo;
import com.janetfilter.core.enums.RuleType;
import com.janetfilter.core.models.FilterRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PutFilter {
    private static Map<String, String> map;

    public static void setRules(List<FilterRule> rules) {
        map = new HashMap<String, String>();

        for (FilterRule rule : rules) {
            if (rule.getType() == RuleType.EQUAL) {
                String[] sections = rule.getRule().split("->", 2);
                if (2 != sections.length) {
                    DebugInfo.output("Invalid record: " + rule + ", skipped.");
                } else {
                    map.put(sections[0], sections[1]);
                }
            }
        }

    }

    public static String testPut(String key, String value) {
        if (null == key) {
            return value;
        } else {
            return map.getOrDefault(key, value);
        }
    }
}
