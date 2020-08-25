package br.com.jardel.converter;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperConverter {

    private static ModelMapper mapper = new ModelMapper();

    public static final <S,D> D convert(S source, Class<D> destination) {
        return mapper.map(source, destination);
    }

    public static final <S,D> List<D> convert(List<S> sources, Class<D> destination) {
        return sources.stream().map(s -> convert(s, destination)).collect(Collectors.toList());
    }
}
