package edu.csuft.zhangan.spider;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FilmMapper {
	@Insert("insert into films(id,title,director,actors,year,area,style,star,rating,poster,quote) values(#{id},#{title},#{director},#{actors},#{year},#{area},#{style},#{star},#{rating},#{poster},#{quote})")
	void insert(Film m);
	
	@Select("select * from films where id=#{pk}")
	Film load(int pk);
	
	@Select("select * from films")
	List<Film> find();
}
