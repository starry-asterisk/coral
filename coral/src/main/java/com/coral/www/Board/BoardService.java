package com.coral.www.Board;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface BoardService {
	
	public void addList(Model model, HttpServletRequest request, String keyword);
	public List<CategoryDTO> categorylist(String permission);
	public BoardDTO detail(String no);
	public boolean likeUpd(String no, int div); 
	public String write(BoardDTO dto);
	public String update(BoardDTO dto);
	public boolean updateCA(CategoryDTO dto);
	public boolean insertCA(CategoryDTO dto);
	public boolean deleteCA(String from, String to);
	public boolean moveCA(String from, String to);
}
