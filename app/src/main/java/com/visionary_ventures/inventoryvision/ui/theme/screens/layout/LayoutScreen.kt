package com.visionary_ventures.inventoryvision.ui.theme.screens.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import type { Metadata } from 'next';
import { Geist, Geist_Mono } from 'next/font/google';
import './globals.css';
import { Toaster } from "@/components/ui/toaster";
import MainLayout from '@/components/layout/main-layout';

@Composable
fun Layout_Screen(navController: NavHostController) {

    const geistSans = Geist ({
        variable: '--font-geist-sans',
        subsets: ['latin'],
    });

    const geistMono = Geist_Mono ({
        variable: '--font-geist-mono',
        subsets: ['latin'],
    });

    export const metadata: Metadata = { title: 'Inventory Vision+',
                                        description: 'Financial Management Application',
    };

    export default function RootLayout ({ children,
    }: Readonly<{
        children: React.ReactNode;
    } >) {
        return (
        <html lang ="en" className = "dark">
        <body className ={ `${geistSans.variable} ${geistMono.variable} antialiased` } >
        <MainLayout >
            { children }
        </MainLayout>
        <Toaster / >
        </body>
        </html>
        );
    }
}

@Preview
@Composable
private fun LayoutPrev() {

    Layout_Screen(rememberNavController())

}