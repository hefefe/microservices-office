package com.ttsw.officenotifier.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailEvent implements Serializable {
    private String to;

    private String subject;

    private Map<String, Object> properties;
}
