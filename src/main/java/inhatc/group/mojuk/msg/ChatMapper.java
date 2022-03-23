package inhatc.group.mojuk.msg;

import java.util.List;
import java.util.Map;

public interface ChatMapper {
	//Mapper인터페이스에 구현된 메서드의 명이 mybatis에 존재하는 Query id와 동일해야 함
	List<Map<String, String>> chatSel();
}
