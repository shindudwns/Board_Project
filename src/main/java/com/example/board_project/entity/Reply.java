package com.example.board_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    //    @Column
//    private int hits;
//    private Board board;  board를 추가하는 것이 맞을까 board한테 reply 목록이 있기때문에 고민
    @JoinColumn(name = "boardId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board; //이걸 왜 여기다 써줘야되는지

    //대댓글구현
/*    - 댓글
		|- 댓글
			|- 댓글
				|- 댓글
					|- 댓글
						|- 댓글
							|- 댓글
								|- 댓글*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Reply parent;

    @JsonIgnoreProperties("parent")
    @OneToMany(mappedBy = "parent" ,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Reply> child;

    //위에처럼이 아닌 왜 이렇게 써야되는지를 적어보자
    /*@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Comment> child = new ArrayList<>();
    */
    @CreationTimestamp
    private Timestamp createTime;

}
