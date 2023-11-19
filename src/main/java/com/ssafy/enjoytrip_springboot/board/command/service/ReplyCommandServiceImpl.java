package com.ssafy.enjoytrip_springboot.board.command.service;

import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyBlockRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyDeleteRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyModifyRequest;
import com.ssafy.enjoytrip_springboot.board.command.mapper.ReplyCommandMapper;
import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import com.ssafy.enjoytrip_springboot.board.common.exception.BoardException;
import com.ssafy.enjoytrip_springboot.board.query.mapper.ReplyQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {
    private final ReplyCommandMapper commandMapper;
    private final ReplyQueryMapper queryMapper;

    @Override
    public Long writeReply(ReplyDto replyDto){
        try{
            Long l = commandMapper.writeReply(replyDto);
            return (long) replyDto.getNo();
        } catch (Exception e ){
            throw new BoardException("댓글 등록이 불가능 합니다.");
        }
    }

    @Override
    public void deleteReply(ReplyDeleteRequest no){
        try{
            ReplyDto replyDto = queryMapper.getReply(no.parse())
                    .orElseThrow(() -> new BoardException("해당 댓글이 존재하지 않습니다."));
            if(!replyDto.isDeleted()) commandMapper.deleteReply(no);
            else throw new BoardException("해당 댓글은 이미 삭제 되었습니다.");
        } catch (Exception e){
            throw new BoardException("댓글 삭제가 불가능 합니다.");
        }
    }

    @Override
    public int modifyReply(ReplyModifyRequest request) {
        try {
            commandMapper.modifyReply(request);
            return request.getNo();
        } catch (Exception e){
            throw new BoardException("댓글을 수정 할 수 없습니다.");
        }
    }

    @Override
    public int blockReply(ReplyBlockRequest request) {
        try {
            commandMapper.blockReply(request);

            return request.getNo();
        } catch (Exception e){
            throw new BoardException("게시글을 블락할 수 없습니다.");
        }
    }

}
