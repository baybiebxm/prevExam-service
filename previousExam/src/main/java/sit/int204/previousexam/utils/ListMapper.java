package sit.int204.previousexam.utils;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import sit.int204.previousexam.dtos.PageDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    private static final ListMapper listMapper = new ListMapper();
    private ListMapper(){}  // ApplicationConfig
    public <S,T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper modelMapper){
        return source.stream().map(entity -> modelMapper.map(entity, targetClass))
                .collect(Collectors.toList());
    }

    public static ListMapper getInstance(){      // ApplicationConfig
        return listMapper;
    }

    public <S,T> PageDTO<T> toPageDTO(Page<S> source, Class<T> targetClass, ModelMapper modelMapper){
        PageDTO<T> pageDTO = modelMapper.map(source, PageDTO.class);
        pageDTO.setContent(mapList(source.getContent(), targetClass, modelMapper));
        return pageDTO;
    }

}
