package edu.csuft.zhangan.spider;



/**
 * ӰƬ����Ϣ��ʵ���ࣩ
 * 
 * @author limc
 *
 */
public class Film implements Comparable<Film>{
	//���ע���ֶ�
	/**
	 * ����
	 */
	int id; 
	
	/**
	 * Ƭ��
	 */
	String title;
	
	/**
	 * ����
	 */
	String director;
	
	/**
	 * ��Ա
	 */
	String actors;
	
	/**
	 * ��ӳ���
	 */
	int year;
	
	/**
	 * ��ӳ����
	 */
	String area;
	
	/**
	 * ����
	 */
	String style;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	/**
	 * ����
	 */
	double star;
	
	/**
	 *�������� 
	 */
	int rating;
	

	
	/**
	 * ������·��
	 */
	String poster;
	
	/**
	 * ��Ҫ
	 */
	String quote;

	
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", director=" + director + ", actors=" +actors + ", year=" + year + ", area=" + area + ", style=" + style +", star=" + star + ", rating=" + rating
				+ ", poster=" + poster + ", quote=" + quote + "]";
	}

	@Override
	public int compareTo(Film arg0) {
		// TODO Auto-generated method stub
		if(this.id > arg0.id)
			return 1;
		else if(this.id < arg0.id)
			return -1;
		return 0;
	}




}
