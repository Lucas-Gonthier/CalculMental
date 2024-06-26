package com.example.calculmental;

import static androidx.core.content.ContentProviderCompat.requireContext;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.calculmental.database.ScoreBaseHelper;
import com.example.calculmental.database.ScoreDao;
import com.example.calculmental.entities.Score;

import java.util.List;

public class ActivityHighscores {
        private ScoreDao scoreDao;
        private ListView listView;


}

