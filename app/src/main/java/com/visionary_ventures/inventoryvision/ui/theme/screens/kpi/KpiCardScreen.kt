package com.visionary_ventures.inventoryvision.ui.theme.screens.kpi

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { cn } from "@/lib/utils";

{ LucideIcon } from "lucide-react";

@Composable
fun KPICARD_Screen(navController:NavHostController) {

    interface KpiCardProps {
        title: string;
        value: string;
        change?: string;
        changeType?: 'positive' | 'negative';
        icon?: LucideIcon | string; // Changed from IconComponent to icon, and type updated
    }

    export function KpiCard({ title, value, change, changeType, icon }: KpiCardProps) {
        return (
        <Card className="card-common">
        <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
        <CardTitle className="text-sm font-medium">{title}</CardTitle>
        {icon && (
            typeof icon === 'string'
            ? <span className="text-lg text-muted-foreground">{icon}</span>
            : (() => {
                const IconComponent = icon; // icon is LucideIcon here
                return <IconComponent className="h-4 w-4 text-muted-foreground" />;
            })()
            )}
        </CardHeader>
        <CardContent>
        <div className="text-2xl font-bold">{value}</div>
        {change && (
            <p className={cn(
                "text-xs text-muted-foreground",
                changeType === 'positive' && "text-emerald-500 dark:text-emerald-400",
                changeType === 'negative' && "text-red-500 dark:text-red-400"
            )}>
                    {change}
            </p>
            )}
        </CardContent>
        </Card>
        );
    }


}

@Preview
@Composable
private fun CardPrev() {

    KPICARD_Screen(rememberNavController())

}