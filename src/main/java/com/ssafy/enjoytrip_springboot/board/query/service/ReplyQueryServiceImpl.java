package com.ssafy.enjoytrip_springboot.board.query.service;

import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import com.ssafy.enjoytrip_springboot.board.query.dto.ReplyResponseDTO;
import com.ssafy.enjoytrip_springboot.board.query.mapper.ReplyQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ssafy.enjoytrip_springboot.board.query.dto.ReplyResponseDTO.convertCommentToDto;

@Service
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {
    private final ReplyQueryMapper queryMapper;

    @Override
    public List<ReplyResponseDTO> getReplies(int no) throws SQLException {
        List<ReplyDto> comments = queryMapper.getReplyList(no);

        List<ReplyResponseDTO> replyResponseDTOList = new ArrayList<>();
        Map<Long, ReplyResponseDTO> commentDTOHashMap = new HashMap<>();

        comments.forEach(c -> {
            ReplyResponseDTO replyResponseDTO = convertCommentToDto(c);
            commentDTOHashMap.put(replyResponseDTO.getId(), replyResponseDTO);
            if (c.getParentNo() != null) {
                commentDTOHashMap.get(c.getParentNo())
                        .getChildren().add(replyResponseDTO);}
            else replyResponseDTOList.add(replyResponseDTO);
        });
        return replyResponseDTOList;

    }
}
