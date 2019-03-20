package com.testing.android.appmonitor.presentation.eventslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.testing.android.appmonitor.MonitorApp;
import com.testing.android.appmonitor.R;
import com.testing.android.appmonitor.domain.InstallationEvent;
import com.testing.android.appmonitor.presentation.eventdetails.EventDetailsFragment;

import java.util.List;

public final class EventsListFragment extends Fragment implements EventsListAdapter.OnEventItemClickListener {

    public static EventsListFragment newInstance() {
        return new EventsListFragment();
    }

    private EventsListAdapter eventListAdapter;
    private RecyclerView recyclerEventsList;
    private EventsListViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders
                .of(this, new EventsListViewModelFactory(MonitorApp.get(requireContext())))
                .get(EventsListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_installations_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventListAdapter = new EventsListAdapter(this);
        recyclerEventsList = view.findViewById(R.id.recycler_history_list);
        recyclerEventsList.setAdapter(eventListAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.loadEventsList()
                .observe(getViewLifecycleOwner(), this::applyEventsList);
    }

    @Override
    public void onDestroyView() {
        eventListAdapter = null;
        recyclerEventsList = null;
        super.onDestroyView();
    }

    private void applyEventsList(@Nullable List<InstallationEvent> list) {
        eventListAdapter.submitList(list);
    }

    @Override
    public void onEventItemClicked(@NonNull InstallationEvent item) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, EventDetailsFragment.newInstance(item.getId()))
                .addToBackStack(null)
                .commit();
    }
}
