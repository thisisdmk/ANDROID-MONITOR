package com.testing.android.appmonitor.presentation.eventslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.testing.android.appmonitor.R;
import com.testing.android.appmonitor.domain.InstallationEvent;


public final class EventsListAdapter extends ListAdapter<InstallationEvent, EventsListAdapter.EventListItemViewHolder> {

    private final OnEventItemClickListener clickListener;

    public EventsListAdapter(@NonNull OnEventItemClickListener clickListener) {
        super(new EventListItemItemDiffCallback());
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public EventListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new EventListItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_event, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventListItemViewHolder eventListItemViewHolder, int position) {
        eventListItemViewHolder.bind(getItem(position));
    }

    private static class EventListItemItemDiffCallback extends DiffUtil.ItemCallback<InstallationEvent> {
        @Override
        public boolean areItemsTheSame(@NonNull InstallationEvent item, @NonNull InstallationEvent anotherItem) {
            return item.getPackageName().equals(anotherItem.getPackageName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull InstallationEvent item, @NonNull InstallationEvent anotherItem) {
            return item.equals(anotherItem);
        }
    }

    class EventListItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewAppName;
        private final TextView textViewDate;
        private InstallationEvent eventItem;

        EventListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAppName = itemView.findViewById(R.id.text_view_event_item_app_name);
            textViewDate = itemView.findViewById(R.id.text_view_event_item_date);
            itemView.setOnClickListener(v -> {
                if (eventItem != null) {
                    clickListener.onEventItemClicked(eventItem);
                }
            });
        }

        void bind(@Nullable final InstallationEvent item) {
            this.eventItem = item;
            if (item != null) {
                textViewAppName.setText(item.getAppName());
                textViewDate.setText(item.getDateAndTime());
            } else {
                textViewAppName.setText("");
                textViewDate.setText("");
            }
        }
    }

    public interface OnEventItemClickListener {
        void onEventItemClicked(@NonNull InstallationEvent item);
    }
}
