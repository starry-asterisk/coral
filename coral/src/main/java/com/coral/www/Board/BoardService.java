/* 
 * BoardService.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Service
public interface BoardService {
	/* 게시판 서비스 인터페이스  */
	
	/** 
	 * 게시물 목록 불러오기
	 * 
	 * @param model
	 * @param request
	 * 페이징 정보
	 * @param keyword
	 * 검색어
	 */
	public void addList(Model model, HttpServletRequest request, String keyword);
	
	
	/**
	 * 게시물 상세조회
	 * 
	 * @param no
	 * 게시물 번호
	 * @return 게시물 정보
	 */
	public BoardDTO detail(String no);
	
	/**
	 * 좋아요 반영
	 * 
	 * @param no
	 * 게시물 번호
	 * @param div
	 * 좋아요 / 싫어요 구분
	 * @return 성공여부
	 */
	public boolean likeUpd(String no, int div); 
	
	/**
	 * 게시물 작성
	 * 
	 * @param dto
	 * @return 게시물 번호
	 */
	public String write(BoardDTO dto);
	
	/**
	 * 게시물 수정
	 * 
	 * @param dto
	 * @return 게시물 번호
	 */
	public String update(BoardDTO dto);
	
	/**
	 * 카테고리 목록
	 * 
	 * @param permission
	 * 권한 분류
	 * @return 카테고리 목록
	 */
	public List<CategoryDTO> categorylist(String permission);

	/**
	 * 카테고리 추가
	 * 
	 * @param dto
	 * @return
	 */
	public boolean insertCA(CategoryDTO dto);
	
	/**
	 * 카테고리 수정
	 * 
	 * @param dto
	 * @return
	 */
	public boolean updateCA(CategoryDTO dto);
	
	/**
	 * 카테고리 삭제
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean deleteCA(String from, String to);
	
	/**
	 * 카테고리 이전
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean moveCA(String from, String to);
}
