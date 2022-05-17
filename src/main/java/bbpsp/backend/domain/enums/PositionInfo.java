package bbpsp.backend.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PositionInfo {
    P, C, _1B, _2B, _3B, SS, IF, OF;

    @JsonCreator
    public static PositionInfo getPositionInfoFromString(String value) {
        for (PositionInfo pos : PositionInfo.values()) {
            if (pos.name().equals(value)) {
                return pos;
            }
        }
        return null;
    }
}
