package br.com.jardel.serialization.converter;

import br.com.jardel.config.WebConfig;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    public YamlJackson2HttpMessageConverter() {
        super(new YAMLMapper(), WebConfig.APPLICATION_YAML);
    }
}
