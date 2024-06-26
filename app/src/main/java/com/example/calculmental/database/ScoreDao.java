package com.example.calculmental.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.calculmental.entities.Score;

import java.util.List;

public class ScoreDao extends BaseDao<Score>{

    public static String userName = "USER_NAME";

    public static String score = "SCORE";

    public static String tableName = "SCORES";
    public ScoreDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected void putValues(ContentValues values, Score entity) {
        values.put(userName, entity.getUserName());
        values.put(score, entity.getScore());
    }

    @Override
    protected Score getEntity(Cursor cursor) {
        Score score = new Score();
        Integer indexUserName = cursor.getColumnIndex(userName);
        Integer indexScore = cursor.getColumnIndex(ScoreDao.score);
        score.setUserName(cursor.getString(indexUserName));
        score.setScore(cursor.getInt(indexScore));
        return score;
    }

    public List<Score> getTopScores(int limit) {
        String sortOrder = score + " DESC";
        return getTopEntities(sortOrder, limit);
    }

}
