package com.itwill.lab05.repository;

import java.time.LocalDateTime;

// MVC 아키텍쳐에서 Model 역할 클래스. DB의 posts 테이블 구조와 같은 클래스.
public class Post {
    private Integer id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdTime; // 컬럼 이름: create_time
    private LocalDateTime modifiedTime; // 컬럼 이름: modified_time

    public Post() {
    }

    public Post(Integer id, String title, String content, String author, LocalDateTime createdTime,
            LocalDateTime modifiedTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return this.modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                ", content='" + getContent() + "'" +
                ", author='" + getAuthor() + "'" +
                ", createdTime='" + getCreatedTime() + "'" +
                ", modifiedTime='" + getModifiedTime() + "'" +
                "}";
    }

    // builder 디자인 패턴

    public static PostBuilder builder() {
        return new PostBuilder();
    }

    public static class PostBuilder {
        private Integer id;
        private String title;
        private String content;
        private String author;
        private LocalDateTime createdTime; // 컬럼 이름: create_time
        private LocalDateTime modifiedTime; // 컬럼 이름: modified_time

        private PostBuilder() {
        }

        public PostBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public PostBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PostBuilder content(String content) {
            this.content = content;
            return this;
        }

        public PostBuilder author(String author) {
            this.author = author;
            return this;
        }

        public PostBuilder createdTime(LocalDateTime createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public PostBuilder modifiedTime(LocalDateTime modifiedTime) {
            this.modifiedTime = modifiedTime;
            return this;
        }

        public Post build() {
            return new Post(id, title,
                    content, author,
                    createdTime, modifiedTime);
        }

    }
}
