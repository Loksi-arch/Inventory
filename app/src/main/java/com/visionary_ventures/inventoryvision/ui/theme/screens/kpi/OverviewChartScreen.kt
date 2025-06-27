package com.visionary_ventures.inventoryvision.ui.theme.screens.kpi

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import { useState, useMemo, useEffect } from 'react';
import { Button } from '@/components/ui/button';
import { Calendar } from '@/components/ui/calendar';
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { ChartContainer, ChartTooltip, ChartTooltipContent, ChartLegend, ChartLegendContent } from '@/components/ui/chart';
import { Bar, BarChart, CartesianGrid, Line, LineChart, XAxis, YAxis, ResponsiveContainer } from 'recharts';
import { format, subDays, addDays } from 'date-fns';
import type { DateRange } from 'react-day-picker';
import { CalendarIcon, LineChart as LineChartIcon, BarChart2 as BarChartIcon } from 'lucide-react';
import type { ChartConfig } from '@/components/ui/chart';

@Composable
fun KPIOVERVIEW_Screen(navController: NavHostController) {

    const generateMockData =(startDate: Date, numDays: number) => {
        return Array.from({ length: numDays }, (_, i) => {
        const date = addDays (startDate, i);
        return {
            date: format(date, 'MMM dd'),
            revenue: Math.floor(Math.random() * 200000)+100000, // Adjusted for Ksh values
            expenses: Math.floor(Math.random() * 150000)+50000, // Adjusted for Ksh values
        };
    });
    };

    const chartConfig = {
        revenue: { label: 'Revenue',
                   color: 'hsl(var(--chart-revenue-positive))', // Green for Revenue
    },
        expenses: { label: 'Expenses',
                    color: 'hsl(var(--chart-expenses-negative))', // Red for Expenses
    },
    } satisfies ChartConfig;

    export default function OverviewChart () {
        const[chartType, setChartType] = useState < 'line' | 'bar'>('line');
        const[dateRange, setDateRange] = useState < DateRange | undefined>(undefined);

        useEffect(() => {
            // Initialize dateRange in useEffect to avoid hydration mismatch
            setDateRange({
                from: subDays(new Date(), 29),
                to: new Date(),
            });
        }, []);

        const data = useMemo (() => {
        if (!dateRange?.from) return [];
        const diffDays = dateRange . to ? Math . ceil ((dateRange.to.getTime() - dateRange.from.getTime()) / (1000 * 60 * 60 * 24)) + 1 : 30;
        return generateMockData(dateRange.from, diffDays > 0 ? diffDays : 30);
    }, [dateRange]);

        const ChartComponent = chartType === 'line' ? LineChart : BarChart;
        const ChartSeriesComponent = chartType === 'line' ? Line : Bar;

        return (
        <div className ="space-y-4">
        <div className ="flex flex-wrap items-center justify-between gap-4">
        <div className ="flex items-center gap-2">
        <Select value ={ chartType } onValueChange ={ (value: 'line' | 'bar') => setChartType(value) } >
        <SelectTrigger className ="w-[180px]">
        <SelectValue placeholder ="Select chart type" />
        </SelectTrigger>
        <SelectContent >
        <SelectItem value ="line">
        <div className ="flex items-center gap-2">
        <LineChartIcon className ="h-4 w-4" /> Line Chart
        </div>
        </SelectItem>
        <SelectItem value ="bar">
        <div className ="flex items-center gap-2">
        <BarChartIcon className ="h-4 w-4" /> Bar Chart
        </div>
        </SelectItem>
        </SelectContent>
        </Select>
        </div>
        <Popover >
        <PopoverTrigger asChild >
        <Button
        variant = "outline"
        className = "w-[280px] justify-start text-left font-normal"
        disabled = { !dateRange } // Disable button while dateRange is not set
        >
        <CalendarIcon className ="mr-2 h-4 w-4" />
        {
            dateRange?.from ? (
            dateRange.to ? (
            <>
            { format(dateRange.from, 'LLL dd, y') } - { format(dateRange.to, 'LLL dd, y') }
            </>
            ) : (
            format(dateRange.from, 'LLL dd, y')
            )
            ) : (
            <span > Loading date range...</span>
            )
        }
        </Button>
        </PopoverTrigger>
        <PopoverContent className ="w-auto p-0" align = "end">
        <Calendar
        initialFocus
        mode = "range"
        defaultMonth = { dateRange?.from }
        selected = { dateRange }
        onSelect = { setDateRange }
        numberOfMonths = { 2 }
        />
        </PopoverContent>
        </Popover>
        </div>

        <ChartContainer config ={ chartConfig } className ="h-[350px] w-full">
        <ResponsiveContainer width ="100%" height = "100%">
        {
            data.length > 0 ? (
            <ChartComponent data ={ data } margin ={ { top: 5, right: 20, left:-20, bottom: 5 } } >
            <CartesianGrid strokeDasharray ="3 3" vertical ={ false } / >
            <XAxis dataKey ="date" tickLine ={ false } axisLine ={ false } tickMargin ={ 8 } / >
            <YAxis tickLine ={ false } axisLine ={ false } tickMargin ={ 8 } tickFormatter ={ (value) => `Ksh ${value / 1000}k` } / >
            <ChartTooltip
            cursor = { false }
            content = {
                <ChartTooltipContent
                indicator = { chartType === 'line' ? 'dot' : 'rectangle' }
                formatter =
                    { (value, name) => [`Ksh ${Number(value).toLocaleString()}`, name.charAt(0).toUpperCase()+name.slice(1)] }
                />
            }
            />
            <ChartLegend content ={ <ChartLegendContent / > } / >
            <ChartSeriesComponent dataKey ="revenue" fill = "var(--color-revenue)" stroke = "var(--color-revenue)" radius ={ chartType === 'bar' ? 4 : undefined } type ={ chartType === 'line' ? "monotone" : undefined } / >
            <ChartSeriesComponent dataKey ="expenses" fill = "var(--color-expenses)" stroke = "var(--color-expenses)" radius ={ chartType === 'bar' ? 4 : undefined } type ={ chartType === 'line' ? "monotone" : undefined } / >
            </ChartComponent>
            ) : (
            <div className ="flex items-center justify-center h-full text-muted-foreground">Loading chart data...</div>
            )
        }
        </ResponsiveContainer>
        </ChartContainer>
        </div>
        );
    }

}

@Preview
@Composable
private fun KpiPrev() {

    KPIOVERVIEW_Screen(rememberNavController())

}
