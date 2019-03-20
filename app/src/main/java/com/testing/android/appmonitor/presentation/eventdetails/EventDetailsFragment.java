package com.testing.android.appmonitor.presentation.eventdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.testing.android.appmonitor.MonitorApp;
import com.testing.android.appmonitor.R;
import com.testing.android.appmonitor.domain.InstallationEvent;

public final class EventDetailsFragment extends Fragment {

    private static final String INSTALLATION_ID_KEY = "installation_event_id_key";
    private TextView textViewAppName;
    private TextView textViewVersion;
    private TextView textViewPackageName;
    private TextView textViewSha1;
    private TextView textViewDate;
    private EventDetailsViewModel viewModel;

    public static EventDetailsFragment newInstance(int eventId) {
        EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(INSTALLATION_ID_KEY, eventId);
        eventDetailsFragment.setArguments(args);
        return eventDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders
                .of(this, new EventDetailsViewModelFactory(getEventId(), MonitorApp.get(requireContext())))
                .get(EventDetailsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewAppName = view.findViewById(R.id.text_view_event_details_appname);
        textViewVersion = view.findViewById(R.id.text_view_event_details_version);
        textViewPackageName = view.findViewById(R.id.text_view_event_details_package_name);
        textViewSha1 = view.findViewById(R.id.text_view_event_details_sha1);
        textViewDate = view.findViewById(R.id.text_view_event_details_date);
        view.findViewById(R.id.button_event_details_launch)
                .setOnClickListener(this::onOpenAppButtonClicked);

        viewModel.getEventDetails()
                .observe(getViewLifecycleOwner(), this::applyEventDetails);
    }

    @SuppressWarnings("unused")
    private void onOpenAppButtonClicked(View view) {
        viewModel.openApp();
    }

    @Override
    public void onDestroyView() {
        textViewAppName = null;
        textViewVersion = null;
        textViewPackageName = null;
        textViewSha1 = null;
        textViewDate = null;
        super.onDestroyView();
    }

    private void applyEventDetails(@NonNull InstallationEvent event) {
        textViewAppName.setText(event.getAppName());
        textViewVersion.setText(event.getVersion());
        textViewPackageName.setText(event.getPackageName());
        textViewSha1.setText(event.getSha1Key());
        textViewDate.setText(event.getDateAndTime());
    }

    private int getEventId() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments.getInt(INSTALLATION_ID_KEY);
        }
        throw new IllegalArgumentException("event id not found");
    }
}
