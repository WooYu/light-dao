package com.feiyan.lightdao.conditionbuilder;

import com.feiyan.lightdao.annotation.CrossJoin;
import com.feiyan.lightdao.annotation.FullJoin;
import com.feiyan.lightdao.annotation.InnerJoin;
import com.feiyan.lightdao.annotation.JoinItem;
import com.feiyan.lightdao.annotation.LeftJoin;
import com.feiyan.lightdao.annotation.NaturalJoin;
import com.feiyan.lightdao.annotation.RightJoin;

class JoinClauseBuilder {

    public static String buildInnerJoinClause(InnerJoin innerJoin){
        StringBuilder whereBuilder = new StringBuilder();
        JoinItem[] items = innerJoin.value();
        if (items.length == 1) {
            JoinItem item = items[0];
            whereBuilder.append(item.firstTable())
                    .append(" INNER JOIN ")
                    .append(item.secondTable())
                    .append(" ON ")
                    .append(item.firstTable()).append(".").append(item.firstColumn())
                    .append("=")
                    .append(item.secondTable()).append(".").append(item.secondColumn());
        } else {
            for (int i = 0; i < items.length - 1; i++){
                whereBuilder.append("(");
            }

            for (int i = 0; i < items.length; i++){
                JoinItem item = items[i];

                if (i == 0) {
                    whereBuilder.append(item.firstTable())
                            .append(" INNER JOIN ")
                            .append(item.secondTable())
                            .append(" ON ")
                            .append(item.firstTable()).append(".").append(item.firstColumn())
                            .append("=")
                            .append(item.secondTable()).append(".").append(item.secondColumn())
                            .append(")");
                } else {
                    whereBuilder.append(" INNER JOIN ")
                            .append(item.secondTable())
                            .append(" ON ")
                            .append(item.firstTable()).append(".").append(item.firstColumn())
                            .append("=")
                            .append(item.secondTable()).append(".").append(item.secondColumn());

                    if (i < items.length - 1) {
                        whereBuilder.append(")");
                    }
                }
            }
        }

        return whereBuilder.toString();
    }

    public static String buildFullJoinClause(FullJoin fullJoin){
        return fullJoin.firstTable() + " FULL JOIN " + fullJoin.secondTable() + " ON " +
                fullJoin.firstTable() + "." + fullJoin.firstColumn() + "=" + fullJoin.secondTable() + "." + fullJoin.secondColumn();
    }

    public static String buildLeftJoinClause(LeftJoin leftJoin){
        return leftJoin.firstTable() + " LEFT JOIN " + leftJoin.secondTable() + " ON " +
                leftJoin.firstTable() + "." + leftJoin.firstColumn() + "=" + leftJoin.secondTable() + "." + leftJoin.secondColumn();
    }

    public static String buildRightJoinClause(RightJoin rightJoin){
        return rightJoin.firstTable() + " RIGHT JOIN " + rightJoin.secondTable() + " ON " +
                rightJoin.firstTable() + "." + rightJoin.firstColumn() + "=" + rightJoin.secondTable() + "." + rightJoin.secondColumn();
    }

    public static String buildCrossJoinClause(CrossJoin crossJoin){
        return crossJoin.firstTable() + " CROSS JOIN " + crossJoin.secondTable();
    }

    public static String buildNaturalJoinClause(NaturalJoin naturalJoin){
        return naturalJoin.firstTable() + " NATURAL JOIN " + naturalJoin.secondTable();
    }
}
