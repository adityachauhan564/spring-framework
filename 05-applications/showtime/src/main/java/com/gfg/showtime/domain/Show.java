package com.gfg.showtime.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gfg.showtime.resource.ShowResource;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shows")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// A date AND a time. The course mapped this to a TIME column, which silently threw the date away.
	@Column(name = "show_time", nullable = false)
	private LocalDateTime showTime;

	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	@ManyToOne
	private Movie movie;

	@ManyToOne
	private Theater theater;

	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	@Builder.Default
	private List<Ticket> tickets = new ArrayList<>();

	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	@Builder.Default
	private List<ShowSeat> seats = new ArrayList<>();

	public static ShowResource toResource(Show show) {
		return new ShowResource(show.getId(), show.getShowTime(), show.getMovie().getId(), show.getTheater().getId(),
				show.getMovie().getTitle(), show.getTheater().getName(), show.getTheater().getCity(),
				ShowSeat.toResource(show.getSeats()), show.getCreatedAt(), show.getUpdatedAt());
	}
}
