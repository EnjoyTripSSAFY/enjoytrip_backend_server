package com.ssafy.enjoytrip_springboot.board.command.service;



import com.ssafy.enjoytrip_springboot.board.command.mapper.BoardCommandMapper;
import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import com.ssafy.enjoytrip_springboot.board.query.mapper.BoardQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardCommandServiceImpl implements BoardCommandService {

    private final BoardQueryMapper query;
    private final BoardCommandMapper command;

    @Override
    public Long writeArticle(BoardDto boardDto) throws Exception {
        command.writeArticle(boardDto);

        return (long) boardDto.getNo();
    }

    @Override
    public long modifyArticle(BoardDto boardDto) throws Exception {
        command.modifyArticle(boardDto);

        return (long) boardDto.getNo();
    }

    @Override
    public void deleteArticle(int articleNo) throws Exception {
        // TODO : BoardDaoImpl의 deleteArticle 호출
        command.deleteArticle(articleNo);
    }

    @Override
    public int blockArticle(int no) throws Exception {
        command.blockArticle(no);

        return no;
    }

}
