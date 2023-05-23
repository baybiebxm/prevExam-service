package sit.int204.previousexam.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    private List<T> content;
    private boolean last;
    private boolean first;
    private Integer totalPages;
    private Integer totalElements;
    private Integer size;
    private Integer page;
    @JsonIgnore
    private Integer number;
    public Integer getPage() {
        return number;
    }
}
