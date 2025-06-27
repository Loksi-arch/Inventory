package com.visionary_ventures.inventoryvision.ui.theme.screens.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import { useEffect } from 'react';
import { useRouter } from 'next/navigation';

@Composable
fun LayoutPage_Screen(navController: NavHostController) {

    export default function HomePage() {
        const router = useRouter();

        useEffect(() => {
            router.replace('/dashboard');
        }, [router]);

        return (
        <div className="flex h-screen items-center justify-center">
        <p>Loading Inventory Vision+...</p>
        {/* You can add a spinner or a loading component here */}
        </div>
        );
    }

}